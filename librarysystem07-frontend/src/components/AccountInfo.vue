<template>
    <div id="accountinfo">
        <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand">McLennon Library Online</a>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="nav navbar-nav" routerLinkActive="active">
                        <a class="nav-item nav-link active" router-link to="/account"> My Account <span class="sr-only">(current)</span></a>
                        <a class="nav-item nav-link" router-link to="/reserve"> Reserve a Book </a>
                        <a class="nav-item nav-link" router-link to="/event"> Request Event </a>
                    </div>
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
                    <input id="address_input" type="text" v-model="new_address" placeholder="New Address">
                    <button id="save_button" v-bind:="!CURRENT_USER_ADDRESS" @click="saveNewAddress(new_address)">Save</button>
                    <p>Address: {{CURRENT_USER_ADDRESS}} </p>  <button id="update_button" v-bind:="!CURRENT_USER_ADDRESS" @click="updateAddress()">Edit</button>
                </td>
            </tr>
            <h2>My Reservations</h2>

        </table>
        <table v-for="reservation in reservations" :key="reservation.reservationID">
            <tr>
            </tr>
            <tr>
                <td>Reservation ID: {{ reservation.reservationID.reservationID }} </td>
            </tr>
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
<script src="./accountinfo.js">
</script>
<style>
    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
        align-content: center;

    }
    a {
        position:sticky;
        display: block;
        padding: 8px;
        background-color: #dddddd;
    }
    tr {
        align-content: center;
        margin-top: 2px;
    }
    #update_button {
        position: relative;
        margin-top: 10px;
        margin-bottom: 10px;
        border: none;
        text-align: left;
        text-decoration: none;
        display: inline-block;
        font-size: 11px;
    }
    #update_button:hover{
        text-decoration: underline;
    }
    #address_input {
        display: none;
        align-self: center;
        overflow:hidden; 
        white-space:nowrap;
    }
    #save_button {
        display: none;
        align-self: center;
        overflow:hidden; 
        white-space:nowrap;
    }
</style>