import { Injectable } from '@angular/core';
import { Profile } from '../model/Profile'
import { RestService} from './restService'

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  restService: RestService<Profile> = new RestService<Profile>("http://localhost:8080/api/profiles");

  async getProfiles(): Promise<Profile[]> {
    const profiles:Profile[] = await this.restService.getAll();
    console.log('Retrieved profiles:', profiles);

    return profiles;
  }

  async getProfile(id:number): Promise<Profile> {
    const profile:Profile = await this.restService.getById(id);
    console.log('Getting profile for:', id, profile);

    return profile;
  }

  async deleteProfile(id:number): Promise<boolean> {
    const result = await this.restService.delete(id);    
    console.log('Deleted profile for:', id);

    return result;
  }

  async upsertProfile(profile:Profile): Promise<number> {
    if (profile.id === 0) {
      await this.restService.create(profile);    
    } else {
      await this.restService.update(profile.id, profile);    
    }

    console.log('Upserted profile for:', profile.id);
    
    return profile.id;
  }
}
