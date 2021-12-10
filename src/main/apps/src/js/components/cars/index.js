import React from 'react'

const ListCars = ({ cars }) => (
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

export default ListCars