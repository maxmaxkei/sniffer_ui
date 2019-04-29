<template>
    <v-toolbar app>
        <v-toolbar-title v-text="title"/>
        <v-spacer/>
    </v-toolbar>
</template>

<script>
    export default {
        name: "Toolbar",
        data() {
            return {
                title: 'Sniffer'
            }
        }
    }
</script>

<style scoped>

</style>