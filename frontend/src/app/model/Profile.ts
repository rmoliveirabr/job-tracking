import { Resume } from "./Resume";

export interface Profile {
  id: number;
  name: string;
  resumes?: Resume[];
}