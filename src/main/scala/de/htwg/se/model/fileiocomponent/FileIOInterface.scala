package de.htwg.se.model.fileiocomponent

import de.htwg.se.controller.controllercomponent.ControllerInterface

trait FileIOInterface {
  def load: ControllerInterface
  def save(controller: ControllerInterface): Unit

}