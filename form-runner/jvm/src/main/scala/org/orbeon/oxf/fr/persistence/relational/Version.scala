package org.orbeon.oxf.fr.persistence.relational

sealed trait  Version
case   object Unspecified                     extends Version
case   object Next                            extends Version
case   class  Specific   (version: Int)       extends Version
case   class  ForDocument(documentId: String) extends Version

object Version {

  val OrbeonForDocumentId              = "Orbeon-For-Document-Id"
  val OrbeonFormDefinitionVersion      = "Orbeon-Form-Definition-Version"

  val OrbeonForDocumentIdLower         = OrbeonForDocumentId.toLowerCase
  val OrbeonFormDefinitionVersionLower = OrbeonFormDefinitionVersion.toLowerCase

  def apply(documentId: Option[String], version: Option[String]): Version =
    documentId match {
      case Some(id) ⇒ ForDocument(id)
      case None     ⇒
        version match {
          case None         ⇒ Unspecified
          case Some("next") ⇒ Next
          case Some(v)      ⇒ Specific(v.toInt)
        }
    }
}