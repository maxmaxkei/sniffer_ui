<template>
        <v-date-picker v-model="datePick"
                       :reactive="reactive"
                       :full-width="fullWidth"
                       :no-title="noTitle"
                       @change="updateDate"
        />
</template>

<script>
    export default {
        name: 'DatePicker',
        data () {
            return {
                datePick: null,
                reactive: true,
                fullWidth: true,
                noTitle: true
            }
        },
        methods: {
            updateDate() {
                this.$emit('change-date', this.datePick);
            }
        }
    }
</script>

<style scoped>

</style>