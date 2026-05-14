import { useParams } from "react-router-dom";

export default function CityDetails() {

    const { id } = useParams();

    return (
        <div>
            <h1>City Details</h1>

            <p>City ID: {id}</p>
        </div>
    );
}