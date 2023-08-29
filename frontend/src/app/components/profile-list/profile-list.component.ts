import { Component, OnInit } from '@angular/core';
import { Profile } from '../../model/Profile';
import { ProfileService } from '../../services/profile.service';

@Component({
  selector: 'app-profile-list',
  templateUrl: './profile-list.component.html',
  styleUrls: ['./profile-list.component.css']
})
export class ProfileListComponent implements OnInit {
  profiles: Profile[] = [];

  constructor(private profileService: ProfileService) { }

  async ngOnInit() {
    this.profiles = await this.profileService.getProfiles();
    console.log('Returned profiles:',this.profiles);
  }

  async deleteProfile(id:number) {
    let result:boolean = await this.profileService.deleteProfile(id);
    if (result)
      this.profiles = await this.profileService.getProfiles();
  }
}