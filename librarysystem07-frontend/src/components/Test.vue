<template>
  <div id="login">
    <h2>Welcome!</h2>
    <h2>
       <span v-if="message" style="color:navy">uifdskbj: {{message}} </span>
    </h2>
    <div class="row">
      <div class="column">
        <table>
          <tr>
              <th>Existing User</th>
          </tr>
          <tr>
              <td>
                  <input type="text" v-model="existingVisitor.username" placeholder="Username">
              </td>
          </tr>
          <tr>
              <td>
                  <input type="text" v-model="existingVisitor.libraryCardId" placeholder="Library Card ID">
              </td>
          </tr>
          <tr>
              <td>
                  <button v-bind:disabled="!existingVisitor" @click="signIn(existingVisitor.username, existingVisitor.libraryCardId)">Log In</button>
              </td>
          </tr>
          <!--tr>
            <td v-for="visitor in visitorIds"  :key="visitor.libraryCardId">
                {{ visitor.libraryCardId }}
            </td>
          </tr-->
        </table>
        <p>
          <span v-if="errorVisitor" style="color:navy">Error: {{errorVisitor}} </span>
        </p>
      </div>

      <div class="column">
        <table>
          <tr>
              <th>New User</th>
          </tr>
          <tr>
              <td>
                  <input type="text" v-model="newVisitor.name" placeholder="Name">
              </td>
          </tr>
          <tr>
              <td>
                  <input type="text" v-model="newVisitor.username" placeholder="Username">
              </td>
          </tr>
          <tr>
              <td>
                  <input type="text" v-model="newVisitor.libraryCardId" placeholder="Library Card ID">
              </td>
          </tr>
          <tr>
              <td>
                  <input type="text" v-model="newVisitor.address" placeholder="Address">
              </td>
          </tr>
          <tr>
              <td>
                  <button v-bind:disabled="!newVisitor" @click="createVisitor(newVisitor.name, newVisitor.username, newVisitor.libraryCardId, newVisitor.address)">Sign Up</button>
              </td>
          </tr>
        </table>
        <p>
          <span v-if="errorNewVisitor" style="color:navy">Error: {{errorNewVisitor}} </span>
        </p>
      </div>
    </div>
  </div>
</template>

<script src="./testing.js">
</script>

<style>
  * {
    box-sizing: border-box;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f1eaef;
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
    padding: 15px 32px;
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