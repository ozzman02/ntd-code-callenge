/* eslint-disable react/prop-types */
import { useEffect, useState } from 'react';
import useAuthorizationContext from '../hooks/UseAuthorizationContext';
import ReactPaginate from "react-paginate";
import { getUserRecords } from '../services/CalculatorService';


export default function UserHistoryComponent() {

    const { getAuthorizationHeader, getUser } = useAuthorizationContext();

    const [userRecords, setUserRecords] = useState([]);

    const [pageNumber, setPageNumber] = useState(0);

    const [totalPages, setTotalPages] = useState(0);

    const { userId } = getUser();

    const authorizationHeader = getAuthorizationHeader();

    const pageNavigationHandler = (event) => {
        console.log(event);
        const selectedPage = event.selected;
        setPageNumber(selectedPage);
    };

    const fetchUserRecords = async () => {
        try {
            const response = await getUserRecords(authorizationHeader, userId, pageNumber);
            setUserRecords(response.data.content);
            setTotalPages(response.data.totalPages);
            setPageNumber(response.data.pageable.pageNumber);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchUserRecords();
    }, [pageNumber])

    return (
        <div className="table-responsive text-nowrap">
            <h3 className="mt-4">User Mathematical Operations History</h3>
            <table className="table table-striped table-bordered mt-4 table-sm">
                <thead>
                    <tr className="text-center">
                        <th>Date</th>
                        <th>Operation</th>
                        <th>Result</th>
                        <th>Amount</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>
                    {userRecords.map((record) => (
                        <tr key={record.id}>
                            <td>{record.createdDate}</td>
                            <td>{record.operationValue}</td>
                            <td>{record.operationResponse}</td>
                            <td>{record.amount}</td>
                            <td>{record.userBalance}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div>
                <ReactPaginate
                    previousLabel={"<"}
                    nextLabel={">"}
                    breakLabel={"..."}
                    breakClassName={"break-me"}
                    pageCount={totalPages}
                    onPageChange={pageNavigationHandler}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"}
                />
            </div>
        </div>
    );
}