/* eslint-disable react/prop-types */
import ReactPaginate from "react-paginate";

export default function UserHistoryComponent({ userRecords, totalPages, pageNavigationHandler }) {

    const onPageNavigationClick = (event) => {
        pageNavigationHandler(event);
    }

    return (
        <div className="table-responsive text-nowrap">
            <h3 className="mt-4">User Mathematical Operations History</h3>
            <table className="table table-striped table-bordered mt-4 table-sm">
                <thead>
                    <tr className="text-center">
                        <th>Date</th>
                        <th>Type</th>
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
                            <td>{record.operationType}</td>
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
                    onPageChange={onPageNavigationClick}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"}
                />
            </div>
        </div>
    );
}