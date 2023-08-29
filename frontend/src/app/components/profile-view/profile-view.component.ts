import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profile } from 'src/app/model/Profile';
import { Resume } from 'src/app/model/Resume';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.css']
})
export class ProfileViewComponent implements OnInit {
  
  public profile?:Profile;
  public resumes: Resume[] = [];

  public id:number;

  constructor(private profileService:ProfileService, private route: ActivatedRoute) {
    this.id = parseInt(this.route.snapshot.paramMap.get('id')!);
  }

  async ngOnInit() {
    this.profile = await this.profileService.getProfile(this.id);
    this.resumes = this.profile.resumes || [];
  }
}
