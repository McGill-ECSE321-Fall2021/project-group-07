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
              <input type="text" placeholder="Visitor Name">
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
              <button v-bind:disabled="!newEvent" @click="createEvent(newEvent.name, newEvent.eventId, newEvent.visitor)">Create</button>
      </tr>
      <tr>
          <ul>
              <li v-for="event in events" :key="event.name">
                  {{event.name}}
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
 #librarysystem07 {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>