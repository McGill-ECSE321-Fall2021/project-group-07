import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function VisitorDto (name, username, address, libraryCardId) {
    this.name = name;
    this.username = username;
    this.address = address;
    this.libraryCardId = libraryCardId;
    this.demeritPoints = 0;
    if (address.includes("Montreal") || address.includes("montreal")) {
        this.balance = 0;
    }
    else {
        this.balance = 15;
    }
    this.events = [];
    this.reservations = [];
}

function InventoryItemDto(inventoryItemID, duplicates, name, author, status, type) {
	
  this.inventoryItemID = inventoryItemID;
  this.duplicates = duplicates;
  this.name = name;
  this.author = author;
  this.status = status;
  this.type = type;
  
  if (type.includes("archive") || type.includes("newspaper") || type.includes("magazine")) {
      this.isReservable = false;
  } else {
      this.isReservable = true;
  }
  
}

function ReservationDto(startDate, endDate, visitor, inventoryItem, reservationId) {
  this.startDate = startDate;
  this.endDate = endDate;
  this.visitor = visitor;
  this.inventoryItem = inventoryItem;
  this.reservationId = reservationId;

}


export default {
    name: 'inventory',
    data () {
      return {
            inventoryItems: [],
            reservations: [],
            CURRENT_USER: localStorage.getItem('USER'),
            errorItem: '',
            response: [],
            item: {
                duplicates: '',
                name: '',
                author: '', 
                status: '', 
                type: ''
            }
        }
    },

    created: function () {

        //TEST DATA if backend won't connect
        const i1 = new InventoryItemDto(0, 1, "To Kill a Mockingbird", "Harper Lee", "available", "book");
        const i2 = new InventoryItemDto(1, 1, "Fruits Magazine", "Shoichi Aoki", "available", "magazine");
        const i3 = new InventoryItemDto(0, 1, "The Great Gatsby", "F. Scott Fitzgerald", "available", "book");
        const i4 = new InventoryItemDto(0, 1, "Hamlet", "William Shakespeare", "available", "book");
        this.inventoryItems.push({item: i1}, {item: i2}, {item: i3}, {item: i4});

        AXIOS.get('/inventoryItem')
        .then(response => {
            // JSON responses are automatically parsed.
            this.inventoryItems = push({item: response.data})
        })
        .catch(e => {
            this.errorItem = e
        }),

        AXIOS.get('/visitors/'.concat(localStorage.getItem('ID')))
           .then(response => {
               // JSON responses are automatically parsed.
               this.CURRENT_USER = response.data
           })
           .catch(e => {
               this.errorItem = e
           }),
           
           AXIOS.get('/reservations/')
           .then(response => {
               // JSON responses are automatically parsed.
               this.reservations = response.data
           })
           .catch(e => {
               this.errorItem = e
           })
        },

        

      methods: {
        createItem: function (duplicates, name, author, status, type) {
            var inventoryItemId = this.inventoryItems.indexOf(x => x.name == itemName);
 
            var isReservable = true;
            if (type == "magazine" || type == "CD" || type == "archive") {
                isReservable = false;
            }

            const i = new InventoryItemDto(inventoryItemId, duplicates, name, author, status, type);
            this.inventoryItems.push({item: i});
            AXIOS.post('/inventoryItem/'.concat(localStorage.getItem('ID')), {}, {params: {
                inventoryItemID: inventoryItemId,
                duplicates: duplicates,
                name: name,
                author: author,
                status: status,
                type: type,
                reservable: isReservable
            }})
            .then(response => {
            // JSON responses are automatically parsed.
                this.inventoryItems.push(response.data)
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorItem = errorMsg
            })

            

          },
          reserveItem: function (itemId) {
            var itemName = itemId.substring(0, itemId.lastIndexOf(" -"));
            const item = this.inventoryItems.find(x => x.name == itemName)

            var today = new Date();
            var currDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            var returnDate = today.getFullYear()+'-'+(today.getMonth()+2)+'-'+today.getDate();
            var id = this.reservations.length;
            const res = new ReservationDto(currDate, returnDate, this.CURRENT_USER, item, id);
            this.reservations.push(res);

            AXIOS.post('/reservations/'.concat(id), {},
                          {params: {
                            startDate: currDate,
                            endDate: returnDate,
                            visitorId: localStorage.getItem('ID'),
                            inventoryItemId: itemId
                            }})
                        .then(response => {
                          // Update appropriate DTO collections
                          visitor.reservations.push({startDate, endDate, id})
                          this.selectedItem = ''
                          this.errorReservation = ''
                        })
                        .catch(e => {
                          var errorMsg = e
                          console.log(errorMsg.message)
                          this.errorReservation = errorMsg
            })
        },
    }
}
