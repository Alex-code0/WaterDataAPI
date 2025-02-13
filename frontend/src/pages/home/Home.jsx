import React, { useState } from 'react'

import './Home.css'

const Home = () => {

    const [statioId, setStationId] = useState("")
    const [year, setYear] = useState("")

    const [DataDate]

    const handleStationIdChange = (e) => {
        setStationId(e.target.value)
    }

    const handleYearChange = (e) => {
        setYear(e.target.value)
    }

    const handleWaterDataRequest = async (e) => {
        e.preventDefault()
        const analysisData = { statioId, year }

        try {
            const request = await fetch("https://localhost:8080/api/waterquality?station=%s&year=%s", stationId, year {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(analysisData)
            })

            const data = await request.json()

            if(Response.ok) {

            } else {
                console.error("Error fetching: ", data.message)
            }
        } catch(error) {
            console.error("Error: " + error)
        }
    }

  return (
    <div className="HomeContainer">
        <form onSubmit={handleWaterDataRequest}>
            <input type="text" placeholder="Station id" value={statioId} onChange={handleStationIdChange} required />
            <input type="text" placeholder="year" value={year} onChange={handleYearChange} required />
            <button type="submit">Search</button>
        </form>
    </div>
  )
}

export default Home
