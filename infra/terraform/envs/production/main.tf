provider "google" {
  project     = var.project
  region      = var.region
  zone        = var.zone
  credentials = file(var.credential_file)
}

module "vm" {
  source         = "../../modules/vm"
  name           = "vm-quarkus"
  machine_type   = "e2-standard-2"
  zone           = var.zone
  ssh_user       = var.ssh_user
  pub_key_file   = var.pub_key_file
  environment    = "dev"
  application    = "quarkus"
  tags           = ["quarkus-vm-costumer"]
}
