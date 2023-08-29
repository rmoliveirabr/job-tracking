SELECT *
FROM `job_tracking`.`profiles`
ORDER BY `id` DESC
LIMIT 100
;

SELECT *
FROM `job_tracking`.`resumes`
ORDER BY `id` DESC
LIMIT 100
;

DELETE FROM `job_tracking`.`resumes` WHERE profile_id is null;

select p.id p_id, p.name, r.id r_id, r.template_name, r.description, r.version, r.profile_id
from `job_tracking`.`profiles` p
left join `job_tracking`.`resumes` r on r.profile_id = p.id
order by p.id desc, r.template_name
limit 100
;
