import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function compareName(a, b) {
    if ( a.name < b.name ){
      return -1;
    }
    if ( a.name > b.name ){
      return 1;
    }
    return 0;
  }

  function compareAuthor(a, b) {
    if ( a.author < b.author ){
      return -1;
    }
    if ( a.author > b.author ){
      return 1;
    }
    return 0;
  }
  
  
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
            response: []
        }
    },

    created: function () {

        //TEST DATA if backend won't connect
        const i1 = new InventoryItemDto(0, 1, "To Kill a Mockingbird", "Harper Lee", "available", "book");
        const i2 = new InventoryItemDto(1, 1, "Fruits Magazine", "Shoichi Aoki", "available", "magazine");
        const i3 = new InventoryItemDto(0, 1, "The Great Gatsby", "F. Scott Fitzgerald", "available", "book");
        const i4 = new InventoryItemDto(0, 1, "Hamlet", "William Shakespeare", "available", "book");
        this.inventoryItems.push({'item': i1}, {'item': i2}, {'item': i3}, {'item': i4});

        //retrieve inventory items
        AXIOS.get('/inventoryItem')
        .then(response => {
            //Push key, value pair so view can access value with key
            this.inventoryItems = push({'item': response.data})
        })
        .catch(e => {
            this.errorItem = e
        }),

        //find current user
        AXIOS.get('/visitors/'.concat(localStorage.getItem('ID')))
           .then(response => {
               // JSON responses are automatically parsed.
               this.CURRENT_USER = response.data
           })
           .catch(e => {
               this.errorItem = e
           }),
           
           //retrieve user's reservations
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
          reserveItem: function (itemId) {
            //display DTO
            var itemName = itemId.substring(0, itemId.lastIndexOf(" -"));
            this.inventoryItems.find(x => x.name == itemName).status = "Reserved";
            const item = this.inventoryItems.find(x => x.name == itemName)
            var today = new Date();
            var currDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            var returnDate = today.getFullYear()+'-'+(today.getMonth()+2)+'-'+today.getDate();
            var id = this.reservations.length;
            const res = new ReservationDto(currDate, returnDate, this.CURRENT_USER, item, id);
            //push DTO to list being displayed
            this.reservations.push(res);

            //save new reservation to backend
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

        //sort items by author
        sortAuthor: function() {
            //use sort with compare function above
            this.inventoryItems['item'].sort(compareAuthor()); //not functional
 
        },

        //sort items by title
        sortTitle: function() {
            this.inventoryItems = Object.entries(this.inventoryItems).sort((a,b) => a[1].name-b[1].name);
            // var sorting = [];
            // for (var key in this.inventoryItems) {
            //     sorting.push
            // }
            // this.inventoryItems.sort(compareName());

        }
    }
}
