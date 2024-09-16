import ReactPaginate from "react-paginate";
import { useState } from "react";

export default function UserHistoryComponent() {

    const [totalPages, setTotalPages] = useState(0);

    const [pageNumber, setPageNumber] = useState(0);

    const pageNavigationHandler = (event) => {
        const selectedPage = event.selected;
        setPageNumber(selectedPage);
    };

    const dummyData = [
        {
            "id": 14,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4950.00,
            "operation": "892*623*359/367*382+415/324*427/892/241*381/665-527+635-38",
            "result": 207656148.49737048,
            "date":"2024-09-15 22:26:21"
        },
        {
            "id": 13,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4955.00,
            "operation": "706+319-129-230-388*190*108+899+78/404*836/801-645/191+110",
            "result": -7960088.175457807,
            "date":"2024-09-15 22:25:40"
        },
        {
            "id": 12,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4960.00,
            "operation": "313+30+77/574-801+214+391*154+262-372/3+988/184*739*305",
            "result": 1270381.2863202544,
            "date":"2024-09-15 22:24:39"
        },
        {
            "id": 11,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4965.00,
            "operation": "906*719-10-848*216*486-481-870/94/847/911/763*325/568-810",
            "result": -88369535.00000001,
            "date":"2024-09-15 22:24:18"
        },
        {
            "id": 10,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4970.00,
            "operation": "464+500+48-935/656*764/910+343-879/666-296/918+869-184+297",
            "result": 2334.161110503112,
            "date":"2024-09-15 22:22:55"
        },
        {
            "id": 9,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4970.00,
            "operation": "464+500+48-935/656*764/910+343-879/666-296/918+869-184+297",
            "result": 2334.161110503112,
            "date":"2024-09-15 22:22:55"
        },
        {
            "id": 8,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4970.00,
            "operation": "464+500+48-935/656*764/910+343-879/666-296/918+869-184+297",
            "result": 2334.161110503112,
            "date":"2024-09-15 22:22:55"
        },
        {
            "id": 7,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4970.00,
            "operation": "464+500+48-935/656*764/910+343-879/666-296/918+869-184+297",
            "result": 2334.161110503112,
            "date":"2024-09-15 22:22:55"
        },
        {
            "id": 6,
            "operationType": "COMPLEX",
            "amount": 5,
            "balance": 4970.00,
            "operation": "464+500+48-935/656*764/910+343-879/666-296/918+869-184+297",
            "result": 2334.161110503112,
            "date":"2024-09-15 22:22:55"
        }
    ]

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
                    {dummyData.map((record) => (
                        <tr key={record.id}>
                            <td>{record.date}</td>
                            <td>{record.operation}</td>
                            <td>{record.result}</td>
                            <td>{record.amount}</td>
                            <td>{record.balance}</td>
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