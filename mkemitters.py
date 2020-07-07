#!/usr/bin/python3

GLOBAL_HEAD = 'package org.unclesniper.puppeteer;\n\nimport org.unclesniper.puppeteer.util.ShorthandName;\n\n'

class Emitter(object):
	def __init__(self, inType, outType, field, methSig, shorthand, adapters):
		self.inType = inType
		self.outType = outType
		self.field = field
		self.methSig = methSig
		self.shorthand = shorthand
		self.adapters = adapters
	def genAdapter(self, wrapped, adaptLines):
		implname = self.inType + self.outType
		wrname = wrapped.inType + wrapped.outType
		clname = wrname + implname
		accname = self.field[0].upper() + self.field[1:]
		f = open('src/org/unclesniper/puppeteer/' + clname + '.java', 'w')
		f.write(GLOBAL_HEAD)
		f.write('@ShorthandName("' + wrapped.shorthand + self.shorthand[0].upper() + self.shorthand[1:] + '")\n')
		f.write('public class ' + clname + ' extends Abstract' + implname + ' {\n\n')
		f.write('\tprivate ' + wrname + ' ' + self.field + ';\n\n')
		f.write('\tpublic ' + clname + '() {}\n\n')
		f.write('\tpublic ' + clname + '(' + wrname + ' ' + self.field + ') {\n')
		f.write('\t\tthis.' + self.field + ' = ' + self.field + ';\n')
		f.write('\t}\n\n')
		f.write('\tpublic ' + wrname + ' get' + accname + '() {\n')
		f.write('\t\treturn ' + self.field + ';\n')
		f.write('\t}\n\n')
		f.write('\tpublic void set' + accname + '(' + wrname + ' ' + self.field + ') {\n')
		f.write('\t\tthis.' + self.field + ' = ' + self.field + ';\n')
		f.write('\t}\n\n')
		f.write('\t@Override\n')
		f.write('\tprotected ' + self.methSig + ' throws PuppetException {\n')
		for line in adaptLines:
			f.write('\t\t' + line + '\n')
		f.write('\t}\n\n')
		f.write('}\n')
		f.close()

STRINGSOURCE_STEP_TO_SCOPE = [
	'if(source != null)',
	'\tsource.buildString(info.getScope(), sink);'
]
STRINGSOURCE_STEP_TO_STEP = [
	'if(source != null)',
	'\tsource.buildString(info, sink);'
]
STRINGSOURCE_OP_TO_SCOPE = [
	'if(source == null)',
	'\treturn;',
	'if(info.stepInfo == null)',
	'\tthrow new MissingStepInfoException();',
	'source.buildString(info.stepInfo.getScope(), sink);'
]
STRINGSOURCE_OP_TO_STEP = [
	'if(source == null)',
	'\treturn;',
	'if(info.stepInfo == null)',
	'\tthrow new MissingStepInfoException();',
	'source.buildString(info.stepInfo, sink);'
]
STRINGSOURCE_OP = {
	'Scope': STRINGSOURCE_OP_TO_SCOPE,
	'Step': STRINGSOURCE_OP_TO_STEP,
	'NetworkStep': STRINGSOURCE_OP_TO_STEP,
	'MachineStep': STRINGSOURCE_OP_TO_STEP
}

EMITTERS = [
	Emitter('Scope', 'StringSource', 'source',
		'void buildStringImpl(ScopeLevel scope, StringBuilder sink)',
		'scopeString', {}),
	Emitter('Step', 'StringSource', 'source',
		'void buildStringImpl(Step.StepInfo info, StringBuilder sink)',
		'stepString', {
			'Scope': STRINGSOURCE_STEP_TO_SCOPE
		}),
	Emitter('NetworkStep', 'StringSource', 'source',
		'void buildStringImpl(NetworkStep.NetworkStepInfo info, StringBuilder sink)',
		'networkStepString', {
			'Scope': STRINGSOURCE_STEP_TO_SCOPE,
			'Step': STRINGSOURCE_STEP_TO_STEP
		}),
	Emitter('MachineStep', 'StringSource', 'source',
		'void buildStringImpl(MachineStep.MachineStepInfo info, StringBuilder sink)',
		'machineStepString', {
			'Scope': STRINGSOURCE_STEP_TO_SCOPE,
			'Step': STRINGSOURCE_STEP_TO_STEP,
			'NetworkStep': STRINGSOURCE_STEP_TO_STEP
		}),
	Emitter('Exec', 'StringSource', 'source',
		'void buildStringImpl(ExecSlave.ExecInfo info, StringBuilder sink)',
		'execString', STRINGSOURCE_OP),
	Emitter('CopyFrom', 'StringSource', 'source',
		'void buildStringImpl(CopySlave.CopyFromInfo info, StringBuilder sink)',
		'copyFromString', STRINGSOURCE_OP),
	Emitter('CopyTo', 'StringSource', 'source',
		'void buildStringImpl(CopySlave.CopyToInfo info, StringBuilder sink)',
		'copyToString', STRINGSOURCE_OP),
	Emitter('DeleteFile', 'StringSource', 'source',
		'void buildStringImpl(FileSlave.DeleteFileInfo info, StringBuilder sink)',
		'deleteFileString', STRINGSOURCE_OP),
	Emitter('NewTempFile', 'StringSource', 'source',
		'void buildStringImpl(FileSlave.NewTempFileInfo info, StringBuilder sink)',
		'newTempFileString', STRINGSOURCE_OP)
]

EMITTERS_BY_IFACE = {}
for emitter in EMITTERS:
	EMITTERS_BY_IFACE[emitter.inType + emitter.outType] = emitter

for emitter in EMITTERS:
	for adapter, lines in emitter.adapters.items():
		emitter.genAdapter(EMITTERS_BY_IFACE[adapter + emitter.outType], lines)
