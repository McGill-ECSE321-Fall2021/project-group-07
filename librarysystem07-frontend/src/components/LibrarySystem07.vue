<template>
<div id="events">
    <h2>Event sign-up</h2>
    <table>
      <tr>
          <td><b>Create your event!</b></td>
      </tr>
      <tr>
          <td>Name of event</td>
          <td>
              <input type="text" v-model="newEvent.name" placeholder="Event Name">
          </td>
      </tr>
      <tr>
          <td>Visitor name</td>
          <td>
              <input type="text" v-model="newEvent.visitor" placeholder="Visitor Name">
          </td>
      </tr>
      <tr>
          <td>&nbsp; Description of event&nbsp; &nbsp; </td>
          <td>
              <textarea id="eventDescription" name="eventDescription" rows="4" cols="50" placeholder="Event Description">
              
              </textarea>

          </td>
      </tr>
      <tr>
              <button v-bind:disabled="!newEvent" @click="createEvent(newEvent.name)">Create</button>
      </tr>
      <tr>
          <ul>
              <li v-for="event in events" :key="event.name">
                  {{event.name.nameE}}
              </li>
          </ul> 
      </tr>
    </table>
    <p>
        <span v-if="errorNewEvent" style="color:navy">Error: {{errorNewEvent}} </span>
    </p>
  </div>
</template>



<script src = "./event.js">
</script>



<style>
 /* #librarysystem07 {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  } */
 * {
    box-sizing: border-box;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f1efe7;
  }
    .row {
    display: flex;
    margin-left:-5px;
    margin-right:-5px;
    padding: 5px;
  }
  .column {
    flex: 50%;
    padding: 5px;
  }
  th {
    padding: 18px;
  }
  td {
    padding: 4px;
  }
  table {
    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    border: 1px solid #ddd;
  }
  button {
    margin-top: 10px;
    margin-bottom: 10px;
    padding: -10px -24px -10px -25px;
    border: 2px solid #2c3e50;
    border-radius: 4px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
  }
  button:hover {
  background-color: #d6c2d0;
  color: white;
}
</style>