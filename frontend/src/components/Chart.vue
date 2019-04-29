<script>
    import { Line } from 'vue-chartjs'

    export default {
        extends: Line,
        props: {
            times: Array,
            devices: Array,
            uniqueDevices: Array
        },
        mounted () {
            this.gradient = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450)
            this.gradient2 = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450)

            this.gradient2.addColorStop(0, 'rgba(83,154,168, 0.9)');
            this.gradient2.addColorStop(0.5, 'rgba(83,154,168, 0.5)');
            this.gradient2.addColorStop(1, 'rgba(83,154,168, 0.2)');

            this.gradient.addColorStop(0, 'rgba(247,108,6, 0.9)');
            this.gradient.addColorStop(0.5, 'rgba(247,108,6, 0.25)');
            this.gradient.addColorStop(1, 'rgba(247,108,6, 0)');


            this.renderChart({
                    labels: this.times,
                    datasets: [
                        {
                            label: 'All devices',
                            borderColor: '#FC2525',
                            pointBackgroundColor: 'white',
                            borderWidth: 1,
                            pointBorderColor: 'white',
                            backgroundColor: this.gradient,
                            data: this.devices
                        },{
                            label: 'Unique devices',
                            borderColor: '#05CBE1',
                            pointBackgroundColor: 'white',
                            pointBorderColor: 'white',
                            borderWidth: 1,
                            backgroundColor: this.gradient2,
                            data: this.uniqueDevices
                        }
                    ]
                },
                {
                    responsive: true,
                    maintainAspectRatio: false
                }
            )
        }
    }
</script>