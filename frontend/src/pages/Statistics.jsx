import {
    Line
} from "react-chartjs-2";

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
} from "chart.js";

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
);

export default function Statistics() {

    const data = {
        labels: [
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri"
        ],

        datasets: [
            {
                label: "Temperature",

                data: [20, 22, 19, 25, 24],

                borderColor: "cyan"
            }
        ]
    };

    return (
        <div>
            <h1>Statistics</h1>

            <div className="chart-container">

                <Line data={data} />

            </div>
        </div>
    );
}