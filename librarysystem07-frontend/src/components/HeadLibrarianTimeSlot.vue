<template>
    <div id="headLibrarianTimeSlot">
                <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <h3 class="navbar-brand">McLennan Library Online</h3>
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <router-link class="nav-item nav-link" to="/info"> My Account </router-link>
                        <router-link class="nav-item nav-link" to="/browse-librarian"> Inventory </router-link> 
                        <router-link class="nav-item nav-link" to="/event"> Request Event </router-link> 
                    </div>
        </nav>
        <h2>Librarian Time Slot Manager</h2>
        <br>
        <table align="center">
            <tr>
                <th><label>Time Slot ID:</label></th>
                <td><input type="text" v-model="librarianTimeSlotId" placeholder="LibrarianTimeSlotID"></td>
            </tr>
            <tr>
                <th><label>Librarian ID:</label></th>
                <td><input type="text" v-model="librarian.libraryCardID" placeholder="Librarian ID"></td>
            </tr>
            <tr>
                <th><label>Day of the Week:</label></th>
                <td><input type="text" v-model="day" placeholder="DayOfWeek aDayofTheWeek"></td>
            </tr>
            <tr>
                <th><label>Start Time:</label></th>
                <td><input type="text" v-model="startTime" placeholder="hh:mm:ss"></td>
            </tr>
            <tr>
                <th><label>End Time:</label></th>
                <td><input type="text" v-model="endTime" placeholder="hh:mm:ss"></td>
            </tr>
        </table>
                 <table align="center">
            <tr>
                <button v-bind:disabled="(!librarianTimeSlotId||!aDayOfTheWeek||!startTime||!endTime||!librarian)" @click="createLibrarianTimeSLot(librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian)">Add Librarian Time Slot</button>
                <button v-bind:disabled="(!librarianTimeSlotId||!aDayOfTheWeek||!startTime||!endTime||!librarian)" @click="updateLibrarianTimeSlot(librarianTimeSlotId,startTime,endTime,aDayOfTheWeek,librarian)">Update Librarian Time Slot</button>
                <button v-bind:disabled="(!librarianTimeSlotId)" @click="deleteLibrarianTimeSlot(deleteLibrarianTimeSlot)">Delete Librarian Time Slot</button>
            </tr>
        </table>
        <br>
                    	<h4>Exisiting Librarian Time Slots:</h4>

        <table align="center">

            <tr>
                <th> Librarian Time Slot ID</th>
                <th> Librarian </th>
                <th> Day </th>
                <th> Start Time </th>
                <th> End Time </th>
                
            <tr v-for= "librariantimeslot in librariantimeslots" :key="librariantimeslot.Librarian">
                <td class="tdstyle">{{ librariantimeslot.librarianTimeSlotId }}</td>
                <td class="tdstyle">{{ librariantimeslot.librarian.libraryCardID }}</td>
                <td class="tdstyle">{{ librariantimeslot.aDayOfTheWeek }}</td>
                <td class="tdstyle">{{ librariantimeslot.startTime }}</td>
                <td class="tdstyle">{{ librariantimeslot.endTime }}</td>
            </tr>
        </table>
     
        <span v-if="errorLibrarianTimeSlot" style="color:red">{{errorLibrarianTimeSlot}}</span>
    </div>
</template>

<script src="./HeadLibrarianTimeSlot.js">

</script>

<style>
button{
    margin: 10px 30px;
    padding: 10px 20px;
    font-size: 14px;
    background-color: #b1dbc9;
    border: none;
    border-radius: 5px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    color: darkslategray;
  }
  button:hover {
  background-color: #93cec1;
  color: white;
  }
tables {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}

td, thstyle {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}

th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}
   h3 {
        padding-top: 27px;
        margin-top: 8px;
    }
</style>