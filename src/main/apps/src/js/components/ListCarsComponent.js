import React, { useState, useEffect } from "react"; 

import CarDataService from '../services/CarDataService';
import PagesTools from "../tools/PagesTools";

const ListCarsComponent = (props) =>{

	const [cars, setCars] = useState([])
	const [message, setMessage] = useState(null)
    
	useEffect(() => {
		refreshCourses()
	}, [])


	const refreshCourses = () => {
		CarDataService.retrieveAllCars()
			.then(response => {  
				setCars(response.data)
			}
		)
	}

    
	return (  
		<table className="table">
					<thead>
							<tr>
									<th>#</th>
									<th>Code</th>
									<th>Name</th>
									<th>Serial Number</th>
									<th></th>
							</tr>
					</thead>
					<tbody>
							{
									cars.map(
											(car, i) => 
											<tr key={car.code}>
													<td>{i+1}</td>
													<td>{car.code}</td>
													<td>{car.name}</td>
													<td>{car.serialNumber}</td>
													<td>
															<a href={'./cars/edit?code='+car.code}>edit</a>
													</td>
											</tr>
									) 
							} 
					</tbody>
			</table> 
	)
    
}

export default ListCarsComponent
