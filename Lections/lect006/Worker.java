public class Worker {

	int id;
	int salary;
	String firstName;
	String lastName;
	

	@Override						// в IDE нужно только начать печатать - <toString>
	public String toString() {		// автогенерация пердложит констукцию с перегрузкой
		String res = String.format("id: %d\nFirst name: %s\nLast name: %s\nSalary: %d\n---", id, firstName, lastName, salary);
		return res;
		// или
		// return String.format("id: %d\nFirst name: %s\nLast name %s\nSalary: %d", id, firstName, lastName, salary);
	}

	@Override
	public int hashCode() { // в больших проектах для ХЭШа прописывается своя функция
		// в качестве ХЭШа в данном случае !ДАННОМ СЛУЧАЕ! будет использоваться только id,
		// что по сути неправильно!!!
		return id;
	}

	public boolean equals(Object o) {
		Worker t = (Worker) o;
		return id == t.id && firstName == t.firstName && lastName == t.lastName && salary == t.salary;
	}
}