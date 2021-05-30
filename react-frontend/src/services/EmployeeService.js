import axios from "axios";

const EMPLOYEES_BASE_URL = "http://localhost:8089/api/v1/employees";

class EmployeeService {
  getEmployees() {
    return axios.get(EMPLOYEES_BASE_URL);
  }

  createEmployee(employee) {
    return axios.post(EMPLOYEES_BASE_URL, employee);
  }

  getEmployeeById(employeeId) {
    return axios.get(EMPLOYEES_BASE_URL + "/" + employeeId);
  }

  updateEmployee(employee, employeeId) {
    return axios.put(EMPLOYEES_BASE_URL + "/" + employeeId, employee);
  }

  deleteEmployee(employeeId) {
    return axios.delete(EMPLOYEES_BASE_URL + "/" + employeeId);
  }
}

export default new EmployeeService();
