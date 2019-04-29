<template>
    <v-navigation-drawer app class="aside" width="400">
        <v-stepper v-model="e6" vertical>
            <v-stepper-step :complete="e6 > 1" step="1" editable>Choose the start</v-stepper-step>
            <v-stepper-content step="1">
                <v-layout row>
                    <v-flex>
                        <p>Date: <strong>{{startSession.date}}</strong></p>
                    </v-flex>
                    <v-flex>
                        <p>Time: <strong>{{startSession.time}}</strong></p>
                    </v-flex>
                </v-layout>
                <DatePicker v-model="startSession.date" @change-date="changeDate('start', $event)"/>
                <TimePicker v-model="startSession.time" @change-time="changeTime('start', $event)"/>
                <v-btn color="primary" @click="e6 = 2">Continue</v-btn>
            </v-stepper-content>
            <v-stepper-step :complete="e6 > 2" step="2" editable>Choose the end</v-stepper-step>
            <v-stepper-content step="2">
                <v-layout row>
                    <v-flex>
                        <p>Date: <strong>{{endSession.date}}</strong></p>
                    </v-flex>
                    <v-flex>
                        <p>Time: <strong>{{endSession.time}}</strong></p>
                    </v-flex>
                </v-layout>
                <DatePicker v-model="endSession.date" @change-date="changeDate('', $event)"/>
                <TimePicker v-model="endSession.time" @change-time="changeTime('', $event)"/>
                <v-btn color="primary" @click="e6 = 3">Continue</v-btn>
                <v-btn flat @click="goBack">Go back</v-btn>
            </v-stepper-content>
            <v-stepper-step :complete="e6 > 3" step="3">Send</v-stepper-step>
            <v-stepper-content step="3">
                <h3>Send a request based on the specified parameters?</h3>
                <p>Start of session's: {{getFullDate('start')}}</p>
                <p>End of session's: {{getFullDate('')}}</p>
                <v-btn color="primary" @click="sendData">Send</v-btn>
                <v-btn flat @click="goBack">Go back</v-btn>
            </v-stepper-content>
        </v-stepper>
    </v-navigation-drawer>
</template>

<script>

    import DatePicker from "./DatePicker";
    import TimePicker from "./TimePicker";

    export default {
        name: "Sidebar",
        components: {
            DatePicker,
            TimePicker
        },
        data() {
            return {
                startSession: {
                    date: '',
                    time: ''
                },
                endSession: {
                    date: '',
                    time: ''
                },
                e6: 1
            }
        },
        methods: {
            changeDate(type, data) {
                if (type === 'start')
                    this.startSession.date = data;
                else
                    this.endSession.date = data;
            },
            changeTime(type, data) {
                if (type === 'start')
                    this.startSession.time = data;
                else
                    this.endSession.time = data;
            },
            goBack() {
                this.e6 -= 1;
            },
            reset() {
                this.changeDate('start', '');
                this.changeTime('start', '');
                this.changeDate('', '');
                this.changeTime('', '');
            },
            sendData() {
                this.$emit('send', [this.startSession, this.endSession]);
                this.$emit('sendForTable', this.dataForTable);
                this.e6 = 1;
                this.reset();
            },
            getFullDate(period){
                if(period === "start")
                    return this.startSession.date + ' ' + this.startSession.time;
                else
                    return this.endSession.date + ' ' + this.endSession.time;
            }
        },
        computed: {
            dataForTable() {
                return [
                    {
                        name: "Beginning of observations",
                        value: this.getFullDate("start")
                    },
                    {
                        name: "End of observations",
                        value: this.getFullDate("")
                    }
                ];
            }
        }
    }
</script>

<style scoped>
    p {
        font-size: 16px;
    }
</style>