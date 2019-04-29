<template>
    <v-app>
        <Sidebar @send="sendRequest"
                 @sendForTable="setTableData"
        />
        <Toolbar/>
        <v-content>
            <div v-if="send" class="spinner__container">
                <orbit-spinner :animation-duration="1200" :size="120" color="#1976d2"/>
            </div>
            <div v-if="chart">
                <Chart :times="times"
                       :devices="devices"
                       :uniqueDevices="uniqueDevices"
                       class="chart"
                />
                <v-flex>
                    <Table :info="dataForTable" class="data__table"/>
                </v-flex>
            </div>
        </v-content>
    </v-app>
</template>

<script>
    /* eslint-disable no-console */

    import Sidebar from "./components/Sidebar";
    import Toolbar from "./components/Toolbar";
    import Chart from "./components/Chart";
    import Table from "./components/Table";
    import { OrbitSpinner } from 'epic-spinners';
    import axios from 'axios';

    export default {
        name: 'App',
        components: {
            Chart,
            Toolbar,
            Sidebar,
            OrbitSpinner,
            Table
        },
        data () {
            return {
                title: 'Sniffer',
                load: false,
                send: false,
                data: null,
                times: null,
                dataForTable: null
            }
        },
        methods: {
            sendRequest(data) {
                const self = this;
                this.send = true;
                this.dataForTable = data;
                let start = data[0].date;
                let end = data[1].date;
                this.daysArr(start, end);
                const startSession = Date.parse(`${data[0].date}T${data[0].time}`)/1000;
                const endSession = Date.parse(`${data[1].date}T${data[1].time}`)/1000;
                const separators = this.times.map((item) => {
                    return Date.parse(item)/1000;
                }).toString();
                axios.get('http://localhost:8080/search', {
                    params: {
                        startSession: startSession,
                        endSession: endSession,
                        separators: separators
                    }
                })
                .then(function (response) {
                    self.data = response.data;
                    self.dataForTable.push({
                        name: "Total number of all devices",
                        value: response.data.allDevices.length
                    });
                    self.dataForTable.push({
                        name: "Total number of unique devices",
                        value: response.data.distinctDevices.length
                    });
                    self.send = false;
                    self.load = true;
                })
                .catch(function (error) {
                    console.log(error);
                });
            },
            daysArr(start, end) {
                const listDate = [];
                let dateMove = new Date(start);
                let startDate = start;

                while (startDate < end){
                    startDate = dateMove.toISOString().slice(0,10);
                    listDate.push(startDate);
                    dateMove.setDate(dateMove.getDate()+1);
                }
                this.times = listDate;
            },
            setTableData(data) {
                this.dataForTable = data;
            }
        },
        computed: {
            chart() {
                return (!this.send) && this.load;
            },
            devices() {
                return this.times.map((device) => {
                    return this.data[device].length;
                });
            },
            uniqueDevices() {
                return this.times.map((device) => {
                    return this.data["distinct"+device].length;
                });
            }
        }
    }
</script>


<style scoped>
    .spinner__container {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .data__table {
        width: 50%;
    }
    .chart {
        margin-bottom: 30px;
    }
</style>
