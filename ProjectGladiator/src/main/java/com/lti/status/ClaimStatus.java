package com.lti.status;


public class ClaimStatus {

		private ClaimStatusType status;
		private String message;
		int claimId;

		public int getClaimId() {
			return claimId;
		}

		public void setClaimId(int claimId) {
			this.claimId = claimId;
		}

		public ClaimStatusType getStatus() {
			return status;
		}

		public void setStatus(ClaimStatusType status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}


		public static enum ClaimStatusType {
			Success, Failure
		}


}
