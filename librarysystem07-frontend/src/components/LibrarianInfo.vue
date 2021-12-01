<template>
    <div id="accountinfo">
        <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <h3 class="navbar-brand">McLennan Library Online</h3>
                <!--div class="collapse navbar-collapse" id="navbarNavAltMarkup"-->
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <router-link class="nav-item nav-link" to="/info"> My Account </router-link>
                        <router-link class="nav-item nav-link" to="/browse-librarian"> Inventory </router-link> 
                        <router-link class="nav-item nav-link" to="/event"> Request Event </router-link> 
                    </div>
        </nav>

        <table>
            <tr>
                <h2>Account Summary</h2>
            </tr>
            <tr>
                <td>
                    <p>Username: {{CURRENT_USER_USERNAME}} </p>
                </td>
            </tr>
            <tr>
               <td>
                    <p>Library Card ID: {{CURRENT_USER_ID}} </p>
                </td>
            </tr>
            <tr>
               <td>
                    <p>Balance: {{CURRENT_USER_BALANCE}} </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Address: {{CURRENT_USER_ADDRESS}} </p> 
                    <button id="update_button" v-bind:="!CURRENT_USER_ADDRESS" @click="updateAddress()">Edit</button>

                    <input id="address_input" type="text" v-model="new_address" placeholder="New Address">
                    <button id="save_button" v-bind:="!CURRENT_USER_ADDRESS" @click="saveNewAddress(new_address)">Save</button>
                </td>
            </tr>
            <h2>My Reservations</h2>
        </table>
        <table v-for="reservation in reservations" :key="reservation.reservationID">
            <tr>
                <td>
                    Start date of reservation: {{ reservation.reservationID.startDate }}  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  End date of reservation: {{ reservation.reservationID.endDate }}
                </td>
            </tr>
            <tr>
                <td>
                    {{ reservation.reservationID.inventoryItem.author }}  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  {{ reservation.reservationID.inventoryItem.name }}
                </td>
            </tr>
            <p>
                <span v-if="errorReservation" style="color:navy">Error: {{errorReservation}} </span>
            </p>
        </table>

    </div>
</template>
<script src="./accountinfolibrarian.js">
</script>
<style>
    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        align-content: center;
    }
    h2 {
        margin-top: 53px;
    }
    h3 {
        padding-top: 30px;
        margin-top: 10px;
    }
    nav, h3, router-link {
        background-color: whitesmoke;
    }

    tr {
        align-content: center;
        margin-top: 2px;
        margin-bottom: 2px;

    }
    #update_button {
        margin-top: 10px;
        margin-bottom: 10px;
        margin-right: 25px;
        border: none;
        text-decoration: none;
        display: inline-block;
        font-size: 11px;
    }
    #update_button:hover{
        text-decoration: underline;
    }
    #address_input {
        margin-left: 640px;
        display: none;
        margin-top: 20px;
        align-self: center;
        overflow:hidden; 
        white-space:nowrap;
    }
    #save_button {
        margin-left: 695px;
        margin-top: 10px;
        display: none;
        align-self: center;
        overflow:hidden; 
        white-space:nowrap;
    }
</style>