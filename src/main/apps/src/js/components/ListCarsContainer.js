import { PropTypes } from 'prop-types'
import React, { useState, useEffect } from 'react'

import CarDataService from '../services/CarDataService'
import ListCars from './ListCars'

const ListCarsComponent = (props) => {

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
		<ListCars cars={cars} /> 
	)
    
}

export default ListCarsComponent
