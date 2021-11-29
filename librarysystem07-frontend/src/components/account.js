// import axios from 'axios'
// var config = require('../../config')

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

// var AXIOS = axios.create({
//   baseURL: backendUrl,
//   headers: { 'Access-Control-Allow-Origin': frontendUrl }
// })

// function VisitorDto (name, username, address, libraryCardId) {
//     this.name = name;
//     this.username = username;
//     this.address = address;
//     this.libraryCardId = libraryCardId;
//     this.demeritPoints = 0;
//     if (address.includes("Montreal") || address.includes("montreal")) {
//         this.balance = 0;
//     }
//     else {
//         this.balance = 15;
//     }
//     this.events = [];
//     this.reservations = [];
// }

// function InventoryItemDto(inventoryItemID, duplicates, name, author, status, type) {
	
//   this.inventoryItemID = inventoryItemID;
//   this.duplicates = duplicates;
//   this.name = name;
//   this.author = author;
//   this.status = status;
//   this.type = type;
  
//   if (type.includes("archive") || type.includes("newspaper") || type.includes("magazine")) {
//       this.isReservable = false;
//   } else {
//       this.isReservable = true;
//   }
  
// }

// function ReservationDto(startDate, endDate, visitor, inventoryItem, reservationId) {

//   this.startDate = startDate;
//   this.endDate = endDate;
//   this.visitor = visitor;
//   this.inventoryItem = inventoryItem;
//   this.reservationId = reservationId;

// }

// export default {
//     name: 'account',
//     data () {
//       return {
//         selectedItem: '',
//         inventoryItems: [],
//         visitors: [],
//         visitorLoggedIn: {
//             name: '',
//             username: '',
//             address: '',
//             libraryCardId: '',
//             demeritPoints: 0,
//             balance: 0
//         },
//         errorVisitorAddress: '',
//         errorReservation: '',
//         response: []
//       }
      
//     }, 
//     created: function () {
//         // Test data
//         const p1 = new VisitorDto('Sam', 'Sam200031', 'haha st 67', 23456)
//         // Sample initial content
//         this.visitors = [p1]
//         // this.visitorLoggedIn.name = 'Sam'
//         // this.visitorLoggedIn.username = 'Sam200031'
//         // this.visitorLoggedIn.address = 'haha st 67'
//         // this.visitorLoggedIn.libraryCardId = 23456

//          // Initializing persons from backend
//          AXIOS.get('/visitors')
//          .then(response => {
//              // JSON responses are automatically parsed.
//              this.visitors = response.data
//          })
//          .catch(e => {
//              this.errorVisitor = e
//          })

//          // Initializing persons from backend
//          AXIOS.get('/inventoryitem')
//          .then(response => {
//              // JSON responses are automatically parsed.
//              this.inventoryItems = response.data
//          })
//          .catch(e => {
//              this.errorVisitor = e
//          })
        
//     },
//     methods: {

//         updateVisitorAddress: function(newVisitorAddress) {
            
//             AXIOS.put('/visitors/'.concat(visitorLoggedIn.libraryCardId), {}, {params: {
//                 address: newVisitorAddress,
//             }})
//             .then(response => {
//             // JSON responses are automatically parsed.
//                 this.visitors.push(response.data)
//                 this.visitorLoggedIn.address = 'newVisitorAddress'
//             })
//             .catch(e => {
//               var errorMsg = e.response.data.message
//               console.log(errorMsg)
//               this.errorVisitorAddress = errorMsg
//             })
//         },

//         reserveItem: function (itemId) {

//             //var indexItem = this.inventoryItems.map(x => x.inventoryItemId).indexOf(itemId)
//             //var invItem = this.inventoryItems[indexItem]
//             var today = new Date();
//             var currDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
//             var returnDate = today.getFullYear()+'-'+(today.getMonth()+2)+'-'+today.getDate();
//             var id = Math.floor(Math.random() * 1000);

//             AXIOS.post('/reservations/'.concat(id), {},
//               {params: {
//                 startDate: currDate,
//                 endDate: returnDate,
//                 visitorId: this.visitorLoggedIn.libraryCardId,
//                 inventoryItemId: itemId
//                 }})
//             .then(response => {
//               // Update appropriate DTO collections
//               visitor.reservations.push({startDate, endDate, id})
//               this.selectedItem = ''
//               this.errorReservation = ''
//             })
//             .catch(e => {
//               var errorMsg = e
//               console.log(errorMsg.message)
//               this.errorReservation = errorMsg
//             })
//           },
//     }

// }

// // document.getElementById("visitorName").innerHTML = "Name: " + this.visitorLoggedIn.name;
// // document.getElementById("visitorUsername").innerHTML = "Username: " + this.visitorLoggedIn.username;
// // document.getElementById("visitorAddress").innerHTML = "Address: " + this.visitorLoggedIn.address;
// // document.getElementById("visitorLibraryCardId").innerHTML = "ID Card Number: " + this.visitorLoggedIn.libraryCardId;
// //w3.displayObject("displayVisitorDetails", visitorLoggedIn);