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
    this.demeritPoints = "0";
    if (address.includes("Montreal") || address.includes("montreal")) {
        this.balance = "0";
    }
    else {
        this.balance = "15";
    }
    this.reservations = [];
}

export default {
    name: 'visitor',
    data () {
      return {
            visitors: [],
            visitorIds: [],
            visitorUsernames: [],
            newVisitor: {
                name: '',
                username: '',
                address: '',
                libraryCardId: '',
                demeritPoints: "0",
                balance: "0"
            },
            existingVisitor: {
                username: '',
                libraryCardId: ''
            },
            errorVisitor: '',
            errorNewVisitor: '',
            message: '',
            response: []
        }
    },

    created: function () {
        //TEST DATA
        // const v1 = new VisitorDto("John", "John1", "Montreal", "0");
        // const v2 = new VisitorDto("Bob", "Bob1", "Montreal", "1");
        // this.visitors[0] = v1;
        // this.visitors[1] = v2;

        // this.visitorIds = [
        //     {libraryCardId: v1.libraryCardId}, 
        //     {libraryCardId: v2.libraryCardId}
        // ]
        // this.visitorUsernames = [
        //     {username: v1.username}, 
        //     {username: v2.username}
        // ]

        // Initializing persons from backend
        AXIOS.get('/visitors')
        .then(response => {
            // JSON responses are automatically parsed.
            this.visitors = response.data
        })
        .catch(e => {
            this.errorVisitor = e
        })
       },

      methods: {
          //sign up function
        createVisitor: function (visitorName, visitorUsername, visitorAddress, visitorLibraryCardId) {
            if (this.visitors.includes(visitorLibraryCardId) || this.visitors.includes(visitorUsername)) {
                this.errorNewVisitor("This ID is invalid")
                return;
            }
            if (this.visitorIds.includes(visitorLibraryCardId) || this.visitorUsernames.includes(visitorUsername)) {
                this.errorNewVisitor("This ID is invalid")
                return;
            }

            //add visitor to backend with AXIOS
            AXIOS.post('/visitors/'.concat(visitorLibraryCardId), {}, {params: {
                libraryCardId: visitorLibraryCardId,
                username: visitorUsername,
                address: visitorAddress,
                demeritPoints: 0
            }})
            .then(response => {
                //push response to visitor list displayed in view
                this.visitors.push(response.data)
                this.visitorUsernames.push({username: visitorUsername})
                this.visitorIds.push({libraryCardId: visitorLibraryCardId})
                //reset fields
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

            //Make a new visitor DTO and add it to display if backend doesn't work
            this.visitors.push(new VisitorDto(visitorName, visitorUsername, visitorAddress, visitorLibraryCardId))
            this.visitorIds.push({libraryCardId: visitorLibraryCardId})            
            this.visitorUsernames.push({username: visitorUsername})

            // Reset the name field for new people
            this.newVisitor.username = ''
            this.newVisitor.name = ''
            this.newVisitor.address = ''
            this.newVisitor.libraryCardId = ''
          },

          signIn: function (visitorUsername, visitorLibraryCardId) {
            for (let i = 0; i < this.visitors.length; i++) {
                if (this.visitors[i].username == visitorUsername && this.visitors[i].libraryCardId == visitorLibraryCardId) {
                    //save in local storage for if backend not working
                    var CURRENT_USER_USERNAME = localStorage.setItem('USERNAME',visitorUsername);
                    var CURRENT_USER_ID = localStorage.setItem('ID',visitorLibraryCardId);
                    var CURRENT_USER_BALANCE = localStorage.setItem('BALANCE',this.visitors[i].balance);
                    var CURRENT_USER_ADDRESS = localStorage.setItem('ADDRESS',this.visitors[i].address);
                    var CURRENT_USER = localStorage.setItem('USER',this.visitors[i]);

                    //go to main page
                    this.$router.push('/info'); 
                }
            }
            
            this.errorVisitor = "Username and ID do not match.";
          }
        }
}
