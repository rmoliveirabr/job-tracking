import { Company } from "./Company";
import { JobRole } from "./JobRole";

export interface JobOpportunity {
  id: number;
  hiringCompany?: Company;
  recruitmentCompany?: Company;
  role: JobRole;
  jobUrl?: string;
  jobDescription?: string;
}
