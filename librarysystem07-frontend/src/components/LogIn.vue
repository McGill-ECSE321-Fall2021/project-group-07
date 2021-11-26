<template>
  <div id="login">
    <h2>Welcome</h2>
    <div class="column">
        <table>
            <tr>
                <th>Existing User</th>
            </tr>
            <tr v-for="visitor in visitors">
                <td>{{ visitor.username }}</td>
            </tr>
            <tr>
                <td>
                    <input type="text" v-model="visitor.username" placeholder="Visitor Username">
                </td>
            </tr>
             <tr v-for="visitor in visitors">
                <td>{{ visitor.libraryCardId }}</td>
            </tr>
            <tr>
                <td>
                    <input type="text" v-model="visitor.libraryCardId" placeholder="Visitor Library Card ID">
                </td>
            </tr>
            <tr>
                <td>
                     <button v-bind:disabled="!visitor" @click="signIn(visitor)">
                     Sign In</button>
                </td>
            </tr>
        </table>
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
                    <button v-bind:disabled="!newVisitor" @click="createVisitor(newVisitor)">Sign Up</button>
                </td>
            </tr>
        </table>
    </div>
    <p>
        <span v-if="errorUser" style="color:red">Error: {{errorUser}} </span>    
    </p>
  </div>
</template>


<script src="./login.js">
</script>
<style>
    #login {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
        display: flex;
        margin-left:-5px;
        margin-right:-5px;
    }

    #column {
        flex: 50%;
        padding: 5px;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
    }

</style>