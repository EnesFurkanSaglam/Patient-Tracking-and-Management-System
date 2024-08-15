import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css'; // Importing a CSS file for styling

const App = () => {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    // Fetching data from the API
    axios.get('http://localhost:8080/admin/list-doctor')
      .then(response => {
        // Saving the data to the state
        setDoctors(response.data);
      })
      .catch(error => {
        console.error('API error:', error);
      });
  }, []);

  return (
    <div className="app-container">
      <h1 className="title">Doctor List</h1>
      <table className="doctor-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Specialty</th>
            <th>Hospital</th>
          </tr>
        </thead>
        <tbody>
          {doctors.map(doctor => (
            <tr key={doctor.doctorId}>
              <td>{doctor.doctorId}</td>
              <td>{doctor.doctorName}</td>
              <td>{doctor.doctorSurname}</td>
              <td>{doctor.expertise}</td>
              <td>{doctor.hospital}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default App;
