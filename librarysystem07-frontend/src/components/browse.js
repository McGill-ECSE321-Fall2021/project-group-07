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
            CURRENT_USER: localStorage.getItem('USER'),
            errorItem: '',
            response: []
        }
    },

    created: function () {

        //TEST DATA if backend won't connect
        const i1 = new InventoryItemDto(0, 1, "To Kill a Mockingbird", "Harper Lee", "available", "book");
        const i2 = new InventoryItemDto(1, 1, "Fruits Magazine", "Shoichi Aoki", "available", "magazine");
        const i3 = new InventoryItemDto(0, 1, "The Great Gatsby", "F. Scott Fitzgerald", "available", "book");
        const i4 = new InventoryItemDto(0, 1, "Hamlet", "William Shakespeare", "available", "book");
        this.inventoryItems.push({item: i1}, {item: i2}, {item: i3}, {item: i4});

        // Initializing persons from backend
        AXIOS.get('/inventoryItem')
        .then(response => {
            // JSON responses are automatically parsed.
            this.inventoryItems = push({item: response.data})
        })
        .catch(e => {
            this.errorItem = e
        })
       },

      methods: {
        createItem: function (inventoryItemID, duplicates, name, author, status, type) {
                //fix this stuff parameters
            AXIOS.post('/inventoryItem/'.concat(visitorLibraryCardId), {}, {params: {
                libraryCardId: visitorLibraryCardId,
                username: visitorUsername,
                address: visitorAddress,
                demeritPoints: 0
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

                        
            var today = new Date();
            var currDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            var returnDate = today.getFullYear()+'-'+(today.getMonth()+2)+'-'+today.getDate();
            var id = Math.floor(Math.random() * 1000);
            
            AXIOS.post('/reservations/'.concat(id), {},
                          {params: {
                            startDate: currDate,
                            endDate: returnDate,
                            visitorId: CURRENT_USER.libraryCardId,
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
