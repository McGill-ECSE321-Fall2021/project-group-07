<template>
    <div id="accountinfo">
        <table>
            <tr>
                <h2>My Account</h2>
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
            <p>
                <span v-if="errorReservation" style="color:navy">Error: {{errorReservation}} </span>
            </p>
        </table>
    </div>
</template>
<script src="./accountinfo.js">
</script>
<style>
</style>