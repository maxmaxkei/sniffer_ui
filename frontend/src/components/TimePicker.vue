<template>
        <v-time-picker v-model="timePick"
                       format="24hr"
                       :full-width="fullWidth"
                       :no-title="noTitle"
                       @change="updateTime"
                       class="time__picker"
        />
</template>

<script>
    export default {
        name: 'TimePicker',
        data () {
            return {
                timePick: null,
                fullWidth: true,
                noTitle: true
            }
        },
        methods: {
            updateTime() {
                this.$emit('change-time', this.timePick);
            }
        }
    }
</script>

<style scoped>
    .time__picker {
        box-shadow: none;
    }
</style>