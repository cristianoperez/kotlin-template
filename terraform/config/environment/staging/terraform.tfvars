# AWS_ACCOUNT_ID
# https://creditas.atlassian.net/wiki/spaces/DEVOPS/pages/196633/AWS
role_arn = "arn:aws:iam::794670448117:role/OrganizationAccountAccessRole"
region   = "us-east-1"

name = "risk-analysis"
squad = "risk-analysis"
owner = "fabricio-rissetto"
environment = "staging"
application = "risk-analysis"

sg_name_ssh_office = "ssh-office-us-east-1-stg"
sg_name_healthcheck_elb_to_ec2 = "elb_healthcheck_ec2"
sg_name_http_office = "http_office"
sg_name_https_office = "http_office"

db_instance_instance_class="db.t2.small"
db_instance_username="postgres"
db_instance_name="riskanalysis"
db_instance_multi_az="false"

launch_configuration_instance_type = "t3.micro"
launch_configuration_ami = "ami-06bec82fb46167b4f"
launch_configuration_key_name = "creditas-dev-us-east-1"
launch_configuration_enable_monitoring = "true"
launch_configuration_root_block_device_volume_size = "10"

autoscaling_group_health_check_grace_period = "120"

ecs_service_load_balancer_container_port = "9294"
ecs_service_load_balancer_container_name = "risk-analysis"

ecs_load_balancer_zone_alias = "stg.creditas.io"
ecs_load_balancer_record_name = "risk-analysis.stg.creditas.io"

ecs_task_definition_container_definitions_template_file = "./config/templates/container_definitions.json.tmpl"

iam_policy_service_description = "Risk Analysis Service Policy"
iam_policy_service_policy_file = "./config/templates/service_policy.json"
iam_role_service_assume_role_policy_file = "./config/templates/service_assume_role.json"
iam_policy_instance_description = "Risk Analysis Policy Instance"
iam_policy_instance_policy_file = "./config/environment/staging/instance_policy.json"
iam_role_instance_assume_role_policy_file = "./config/templates/instance_assume_role.json"
