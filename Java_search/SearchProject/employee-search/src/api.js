export async function fetchEmployee(empNo) {
    try {
        const response = await fetch(`http://localhost:3000/employees?empNo=${empNo}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('직원 데이터 가져오기 오류:', error);
        throw error;
    }
}