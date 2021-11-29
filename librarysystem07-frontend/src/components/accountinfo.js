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
}


export default {
    name: 'accountinfo',
    data () {
      return {
            CURRENT_USER_USERNAME: localStorage.getItem('USERNAME'),
            CURRENT_USER_ID: localStorage.getItem('ID'),
            CURRENT_USER_BALANCE: 0,
            current_user: [],
            reservations: [],
            user_reservations: [],
            errorReservation: '',
            response: []
        }
    },

    created: function () {

        //TEST DATA
        const inventoryItem1 = new InventoryItemDto(5, 1, "penis", "raffi", "fuck", "die")
        const reservation1 = new ReservationDto("12-11-2021", "12-12-2021", this.current_user, inventoryItem1, 10)
        
        this.reservations =[{reservationID: reservation1 }]

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

        this.reservations =[{ reservationID: reservation1.reservationID, reservationStartDate: reservation1.startDate,
        reservationEndDate: reservation1.endDate, reservationAuthor: reservation1.inventoryItem.author }]

        this.user_reservations.push({id:
            this.reservations.find(reservation => reservation.username == CURRENT_USER_USERNAME)})


       },

      methods: {
        createVisitor: function (visitorName, visitorUsername, visitorAddress, visitorLibraryCardId) {

            if (this.visitors.includes(visitorLibraryCardId) || this.visitors.includes(visitorUsername)) {
                this.errorNewVisitor("This ID is invalid")
                return;
            }
            if (this.visitorIds.includes(visitorLibraryCardId) || this.visitorUsernames.includes(visitorUsername)) {
                this.errorNewVisitor("This ID is invalid")
                return;
            }

            AXIOS.post('/visitors/'.concat(visitorLibraryCardId), {}, {params: {
                libraryCardId: visitorLibraryCardId,
                username: visitorUsername,
                address: visitorAddress,
                demeritPoints: 0
            }})
            .then(response => {
            // JSON responses are automatically parsed.
                this.visitors.push(response.data)
                this.visitorUsernames.push(visitorUsername)
                this.visitorIds.push(visitorLibraryCardId)
                this.newVisitor.username = ''
                this.newVisitor.name = ''
                this.newVisitor.address = ''
                this.newVisitor.libraryCardId = ''
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorNewVisitor = errorMsg
            })

            
          },

          signIn: function (visitorUsername, visitorLibraryCardId) {
       
            for (let i = 0; i < this.visitors.length; i++) {
                if (this.visitors[i].username == visitorUsername) {
                    var USER_USERNAME = localStorage.setItem('USERNAME',visitorUsername);
                    var USER_ID = localStorage.setItem('ID',visitorLibraryCardId);

                    this.$router.push('/app'); 
                }
            }
            this.errorVisitor = "Username and ID do not match.";
          }
        }
}
