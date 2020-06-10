package asg.threads;

public enum TenantIdentifier {
	ONE("One"), TWO("Two");

	private String name;

	TenantIdentifier(String name) {
		this.name = name;
	}
}
