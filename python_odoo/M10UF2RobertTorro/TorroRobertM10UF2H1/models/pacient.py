from odoo import models, fields, api

class Patient(models.Model):
    _name = 'hospitalrtm.patient'
    _description = 'Pacient'

    surname = fields.Char(string='Cognom', required=True, help='Cognom del pacient')
    name = fields.Char(string='Nom', required=True, help='Nom del pacient')
    dni = fields.Char(string='DNI', required=True, help='DNI del pacient')
    gender = fields.Selection([('male', 'Masculí'), ('female', 'Femení')], string='Gènere', help='Gènere del pacient')
    birth_date = fields.Date(string='Data de naixement', required=True, help='Data de naixement del pacient')
    nationality = fields.Many2one('res.country', string='Nacionalitat', required=True, help='Nacionalitat del pacient')
    admission = fields.Boolean(string='Ingrés?', required=True, default=False, help='Està ingressat el pacient?')
    admission_date = fields.Date(string='Data d\'ingrés', help='Data d\'ingrés del pacient')
    info = fields.Text(string='Informació', help='Informació addicional')
    insurance = fields.Char(string='Assegurança', help='Informació de la assegurança del pacient')

    
