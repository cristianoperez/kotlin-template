terraform {
    required_version = ">= 0.11.9"
    backend "s3" {}
}

# Setting AWS region
provider "aws" {
  version = "~> 1.55"
  region = "${var.region}"

  assume_role {
    role_arn = "${var.role_arn}"
  }
}

module "standard_application" {
  source = "git::ssh://git@github.com/creditas/terraform-applications.git//modules/standard_application_with_rds?ref=missing-route53-configurations"

  name = "${var.name}"
  squad = "${var.squad}"
  owner = "${var.owner}"
  environment = "${var.environment}"
  application = "${var.application}"
  region      = "${var.region}"

  db_instance_instance_class = "${var.db_instance_instance_class}"
  db_instance_username = "${var.db_instance_username}"
  db_instance_name = "${var.db_instance_name}"
  db_instance_multi_az = "${var.db_instance_multi_az}"

  sg_name_ssh_office = "${var.sg_name_ssh_office}"
  sg_name_healthcheck_elb_to_ec2 = "${var.sg_name_healthcheck_elb_to_ec2}"
  sg_name_http_office = "${var.sg_name_http_office}"
  sg_name_https_office = "${var.sg_name_https_office}"

  launch_configuration_instance_type = "${var.launch_configuration_instance_type}"
  launch_configuration_ami = "${var.launch_configuration_ami}"
  launch_configuration_key_name = "${var.launch_configuration_key_name}"
  launch_configuration_enable_monitoring = "${var.launch_configuration_enable_monitoring}"
  launch_configuration_root_block_device_volume_size = "${var.launch_configuration_root_block_device_volume_size}"

  route53_record_alias_zone = "${var.route53_record_alias_zone}"
  route53_record_name       = "${var.route53_record_name}"

  autoscaling_group_health_check_grace_period = "${var.autoscaling_group_health_check_grace_period}"

  ecs_service_load_balancer_container_port = "${var.ecs_service_load_balancer_container_port}"
  ecs_service_load_balancer_container_name = "${var.ecs_service_load_balancer_container_name}"

  ecs_task_definition_container_definitions_template_file = "${var.ecs_task_definition_container_definitions_template_file}"

  iam_policy_service_description = "${var.iam_policy_service_description}"
  iam_policy_service_policy_file = "${var.iam_policy_service_policy_file}"
  iam_role_service_assume_role_policy_file = "${var.iam_role_service_assume_role_policy_file}"
  iam_policy_instance_description = "${var.iam_policy_instance_description}"
  iam_policy_instance_policy_file = "${var.iam_policy_instance_policy_file}"
  iam_role_instance_assume_role_policy_file = "${var.iam_role_instance_assume_role_policy_file}"
}
