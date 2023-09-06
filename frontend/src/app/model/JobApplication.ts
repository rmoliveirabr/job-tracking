import { JobOpportunity } from "./JobOpportunity";
import { Resume } from "./Resume";

export interface JobApplication {
    id: number;
    date: Date;
    jobOpportunity: JobOpportunity;
    resume: Resume;
    status: string;
    probability: number;
}