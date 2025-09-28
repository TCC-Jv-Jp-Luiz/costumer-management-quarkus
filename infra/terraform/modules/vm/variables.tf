variable "name" {}
variable "machine_type" {}
variable "zone" {}
variable "image" { default = "ubuntu-2204-lts" }
variable "network" { default = "default" }

variable "ssh_user" { default = "joao" }
variable "pub_key_file" { default = "~/.ssh/id_ed25519.pub" }

variable "tags" {
  type = list(string)
  default = []
}

variable "environment" {}
variable "application" {}
