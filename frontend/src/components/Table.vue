<template>
    <v-data-table
            :items="info"
            :headers="headers"
            hide-actions
            class="elevation-1 table"
    >
        <template slot="items" slot-scope="props">
            <td>{{ props.item.name }}</td>
            <td class="text-xs-left">{{ props.item.value }}</td>
        </template>
    </v-data-table>
</template>

<script>
    export default {
        name: 'Table',
        props: {
            info: Array
        },
        data() {
            return {
                headers: [
                    {text: 'Categories', value: 'categories', sortable: false},
                    {text: 'Value', value: 'value', sortable: false}
                ]
            }
        }
    }
</script>

<style scoped>
    .table td{
        font-size: 14px;
        font-weight: bolder;
    }
</style>