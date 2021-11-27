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
                demeritPoints: 0,
                balance: 0
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
        const v1 = new VisitorDto("John", "John1", "Montreal", "0");
        const v2 = new VisitorDto("Bob", "Bob1", "Montreal", "1");
        this.visitors[0] = v1;
        this.visitors[1] = v2;

        this.visitorIds = [
            {libraryCardId: v1.libraryCardId}, 
            {libraryCardId: v2.libraryCardId}
        ]
        this.visitorUsernames = [
            {username: v1.username}, 
            {username: v2.username}
        ]

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

            // Create a new person and add it to the list of people
            this.visitors.push(new VisitorDto(visitorName, visitorUsername, visitorAddress, visitorLibraryCardId))
            this.visitorIds.push(visitorLibraryCardId)
            this.visitorUsernames.push(visitorUsername)
            if (this.visitors.length > 2) {
                this.message = this.visitors[1].libraryCardId
            }
            // Reset the name field for new people
            this.newVisitor.username = ''
            this.newVisitor.name = ''
            this.newVisitor.address = ''
            this.newVisitor.libraryCardId = ''
          },

          signIn: function (visitorUsername, visitorLibraryCardId) {
            if (this.visitors.length > 2) {
                this.message = this.visitors[2].libraryCardId
            }
            for (let i = 0; i < this.visitors.length; i++) {
                if (this.visitors[i].username == visitorUsername) {
                    this.$router.push('/app'); 
                }
            }
            this.errorVisitor = "Username and ID do not match.";
          }
        }


    //       signIn: function (username, libraryCardId) {
    //         if (visitors.get(libraryCardId).username == username) {
    //             router.push("/visitors/".concat(libraryCardId).concat("/mainpage"))
    //         }
    //       },
}
