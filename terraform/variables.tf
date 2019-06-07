variable "role_arn" {
  description = "Role arn"
}

variable "region" {
  default = "us-east-1"
}

# TAGS

variable "name" {
  description = "Tag name"
}

variable "squad" {
  description = "Tag Squad"
}

variable "owner" {
  description = "Tag Owner"
}

variable "environment" {
  description = "Tag Environment"
}

variable "application" {
  description = "Tag Application"
}

# SECURITY GROUP

variable "sg_name_ssh_office" {
  description = "The name of the Security Group responsible for SSH rules from the Office."
}

variable "sg_name_healthcheck_elb_to_ec2" {
  description = "The name of the Security Group responsible for Healthcheck rules from ELB against our EC2 instances."
}

variable "sg_name_http_office" {
  description = "The name of the Security Group repsonsible for HTTP rules from the Office"
}

variable "sg_name_https_office" {
  description = "The name of the Security Group repsonsible for HTTPS rules from the Office"
}

# RDS

variable "db_instance_instance_class" {
  description = "Instance type used in RDS"
}

variable "db_instance_name" {
  description = "Instance name used in RDS"
}

variable "db_instance_username" {
  description = "Username for the master DB user"
}

variable "db_instance_multi_az" {
  description = "Specifies if the RDS instance is multi-AZ."
}

# ECS

variable "launch_configuration_instance_type" {
  description = "The size of instance to launch."
}

variable "launch_configuration_ami" {
  description = "The EC2 image ID to launch."
}

variable "launch_configuration_key_name" {
  description = "The key name that should be used for the instance."
}

variable "launch_configuration_enable_monitoring" {
  description = "Enables detailed monitoring for launch configuration."
}

variable "launch_configuration_root_block_device_volume_size" {
  description = "Instance volume size."
}

variable "autoscaling_group_health_check_grace_period" {
  description = "Time to wait before considering health check failures inside the instance"
}

variable "ecs_service_load_balancer_container_name" {
  description = "The name of the container to associate with the load balancer (as it appears in a container definition)."
}

variable "ecs_service_load_balancer_container_port" {
  description = "The port on the container to associate with the load balancer. "
}

variable "ecs_task_definition_container_definitions_template_file" {
  description = "A list of valid container definitions provided as a single valid JSON document. Please note that you should only provide values that are part of the container definition document. For a detailed description of what parameters are available"
}

variable "iam_policy_service_description" {
  description = "The description of the policy."
}

variable "iam_policy_service_policy_file" {
  description = "The policy file document of the policy."
}

variable "iam_role_service_assume_role_policy_file" {
  description = "The policy that grants an entity permission to assume the role."
}

variable "iam_policy_instance_description" {
  description = "The description of the policy."
}

variable "iam_policy_instance_policy_file" {
  description = "The policy file document of the policy."
}

variable "iam_role_instance_assume_role_policy_file" {
  description = "The policy that grants an entity permission to assume the role."
}

# Route 53

variable "route53_record_alias_zone" {
  description = "DNS zone name"
}

variable "route53_record_name" {
  description = "DNS record name for the application load balancer"
}
