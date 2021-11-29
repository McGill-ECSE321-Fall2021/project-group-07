import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ReservationDto (startDate, endDate, visitor, inventoryItem, reservationID) {
    this.startDate=startDate;
	this.endDate=endDate;
	this.visitor=visitor;
	this.inventoryItem=inventoryItem;
	this.reservationID=reservationID;
}

function VisitorDto (name, username, address, libraryCardId) {
    this.name = name;
    this.username = username;
    this.address = address;
    this.libraryCardId = libraryCardId;
    this.demeritPoints = "0";
    if (address.includes("Montreal") || address.includes("montreal")) {
        this.balance = "0";
    }
    else {
        this.balance = "15";
    }
}

function InventoryItemDto (inventoryItemID, duplicates, name, author, status, typeOfItem) 
{
    this.inventoryItemID = inventoryItemID;
    this.duplicates = duplicates;
    this.name = name;
    this.author = author;
    this.status = status;
    this.type = typeOfItem;
  
  if (type.includes("archive") || type.includes("newspaper") || type.includes("magazine")) {
      this.isReservable = false;
  } else {
      this.isReservable = true;
  }
}


export default {
    name: 'accountinfo',
    data () {
      return {
            new_address:'',
            CURRENT_USER_USERNAME: localStorage.getItem('USERNAME'),
            CURRENT_USER_ID: localStorage.getItem('ID'),
            CURRENT_USER_BALANCE: localStorage.getItem('BALANCE'),
            CURRENT_USER_ADDRESS: localStorage.getItem('ADDRESS'),

            current_user: [],
            reservations: [],
            user_reservations: [],
            errorReservation: '',
            response: [],    
        
            selectedItem: '',
            inventoryItems: [],
        }
    },

    created: function () {
      
        
        //TEST DATA
        const inventoryItem1 = new InventoryItemDto(5, 1, "i am an item", "i am an author", "idk", "idk")
        const reservation1 = new ReservationDto("12-11-2021", "12-12-2021", this.current_user, inventoryItem1, 10)
        const reservation2 = new ReservationDto("12-12-2021", "12-15-2021", this.current_user, inventoryItem1, 11)

        this.reservations =[{reservationID: reservation1 }, {reservationID: reservation2}]

        AXIOS.get('/visitors/'.concat(CURRENT_USER_ID))
        .then(response => {
            // JSON responses are automatically parsed.
            this.current_user = response.data
            this.CURRENT_USER_BALANCE = response.data.balance
        })
        .catch(e => {
            this.errorReservation = e
        })

       AXIOS.get('/reservations')
        .then(response => {
            // JSON responses are automatically parsed.
            this.reservations = response.data
        })
        .catch(e => {
            this.errorReservation = e
        })
      
        AXIOS.get('/inventoryitem')
         .then(response => {
             // JSON responses are automatically parsed.
             this.inventoryItems = response.data
         })
         .catch(e => {
             this.errorVisitor = e
         })

        this.user_reservations = this.reservations.filter(x => x.visitor.username == CURRENT_USER_USERNAME)
        this.reservations =[{ reservationID: reservation1.reservationID, reservationStartDate: reservation1.startDate,
        reservationEndDate: reservation1.endDate, reservationAuthor: reservation1.inventoryItem.author }]

       },

      methods: {
        saveNewAddress: function (new_address) {
           

            AXIOS.put('/visitors/'.concat(this.CURRENT_USER_ID), {}, {params: {
                address: this.new_address
            }})
            .then(response => {
            // JSON responses are automatically parsed.
                
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorVisitorAddress = errorMsg
            })

            if (!this.errorVisitorAddress){
                var x = document.getElementById("address_input");
                var y = document.getElementById("save_button");
                x.style.display = "none";
                y.style.display = "none";
    
                this.CURRENT_USER_ADDRESS = new_address;
                localStorage.setItem('ADDRESS', CURRENT_USER_ADDRESS);
            }
        },

        updateAddress: function () {
            var x = document.getElementById("address_input");
            var y = document.getElementById("save_button");
            if (x.style.display == "none") {
                x.style.display = "block";
                y.style.display = "block";
            } else {
                x.style.display = "none";
                y.style.display = "none";
            }
        },
        
        reserveItem: function (itemId) {

            //var indexItem = this.inventoryItems.map(x => x.inventoryItemId).indexOf(itemId)
            //var invItem = this.inventoryItems[indexItem]
            var today = new Date();
            var currDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            var returnDate = today.getFullYear()+'-'+(today.getMonth()+2)+'-'+today.getDate();
            var id = Math.floor(Math.random() * 1000);

            AXIOS.post('/reservations/'.concat(id), {},
              {params: {
                startDate: currDate,
                endDate: returnDate,
                visitorId: this.visitorLoggedIn.libraryCardId,
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
          }
      }
}
