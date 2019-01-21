package eyrie

package object syntax {
  object all extends AllSyntax
  object convertible extends ConvertibleSyntax
  object descendant extends DescendantSyntax
  object potentialSuccessor extends PotentialSuccessorSyntax
  object segment extends SegmentSyntax
  object subdivision extends SubdivisionSyntax
  object successor extends SuccessorSyntax

  object diPotentialSuccessor extends DiPotentialSuccessorSyntax
  object diSuccessor extends DiSuccessorSyntax
  object equality extends EqualitySyntax
}
