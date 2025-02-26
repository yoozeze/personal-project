import React, { useState } from 'react';
import { fetchEmployee } from './api';

function EmployeeSearch() {
    const [empNo, setEmpNo] = useState('');
    const [employeeData, setEmployeeData] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(null);

    const handleInputChange = (event) => {
        setEmpNo(event.target.value);
        setError(null);
    };

    const handleSearch = async () => {
        setLoading(true);
        setError(null);

        try {
            const data = await fetchEmployee(empNo);
            setEmployeeData(data);
        } catch (error) {
            console.error('검색 중 오류 발생: ', error);
            setError('직원 데이터를 가져오는 데 실패했습니다.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h1>사원 검색</h1>
            <input type="text" placeholder="사원 번호를 입력하세요" value={empNo} onChange={handleInputChange}></input>
            <button onClick={handleSearch} disabled={loading}>검색</button>

            {loading && <p>검색 중입니다..</p>}

            {error && <p style={{ color: 'red' }}>{error}</p>}

            { employeeData && (
                <div>
                    <h2>검색 결과</h2>
                    <p>사원번호: {employeeData.emp_no}</p>
                    <p>이름: {employeeData.first_name} {employeeData.last_name}</p>
                    <p>성별: {employeeData.gender}</p>
                    <p>입사일: {employeeData.hire_date}</p>
                </div>
            )}
        </div>
    );
}

export default EmployeeSearch