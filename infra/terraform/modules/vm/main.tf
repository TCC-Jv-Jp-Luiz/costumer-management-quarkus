resource "google_compute_instance" "vm_quarkus_instance" {
  name         = var.name
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = var.image
    }
  }

  network_interface {
    network       = var.network
    access_config {}
  }

  metadata = {
    ssh-keys = "${var.ssh_user}:${file(var.pub_key_file)}"
  }

  tags = var.tags

  labels = {
    environment = var.environment
    application = var.application
  }

  allow_stopping_for_update = true
}
